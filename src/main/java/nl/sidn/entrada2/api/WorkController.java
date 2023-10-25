package nl.sidn.entrada2.api;

import java.util.Optional;
import org.apache.iceberg.DataFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import nl.sidn.entrada2.api.data.Work;
import nl.sidn.entrada2.api.data.WorkResult;
import nl.sidn.entrada2.service.IcebergService;
import nl.sidn.entrada2.service.WorkQueueService;

@RestController
@Slf4j
@RequestMapping
@Profile("controller")
public class WorkController implements BaseWork {

  @Autowired
  private WorkQueueService queueService;
  @Autowired
  private IcebergService icebergService;
  
  @Override
  public ResponseEntity<Void> status(long id, WorkResult result) {
    log.info("Received work status from: {} for file: {}", result.getWorker(), result.getFilename());

    long rows = 0;
    for(byte[] bytes: result.getDataFiles()) {
      DataFile df = (DataFile)SerializationUtils.deserialize(bytes);
      rows = rows + df.recordCount();
      icebergService.addDataFile(df);
    }

    queueService.saveResult(result, rows);
    return new ResponseEntity<>(HttpStatus.OK);

  }

  @Override
  public ResponseEntity<Work> work() {
    if(log.isDebugEnabled()) {
      log.debug("Received request for work");
    }
    
    Optional<Work> ow = queueService.getWork();
    if(ow.isPresent()) {
      return new ResponseEntity<>(ow.get(), HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

}