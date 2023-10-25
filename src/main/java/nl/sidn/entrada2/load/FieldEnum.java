package nl.sidn.entrada2.load;

// ENUM for fast access to the index of a avro record field, this saves an additional looklup to
// find index for field name.
public enum FieldEnum {
  // make sure the order of fields matches the order used in dns-query.avsc schema
  id,
  time,
  qname,
  domainname,
  ip_frag,
  ttl,
  ipv,
  prot,
  src,
  srcp,
  dst,
  dstp,
  aa,
  tc,
  rd,
  ra,
  z,
  ad,
  cd,
  ancount,
  arcount,
  nscount,
  qdcount,
  opcode,
  rcode,
  qtype,
  qclass,
  country,
  asn,
  asn_organisation,
  edns_udp,
  edns_version,
  edns_do,
  edns_options,
  edns_client_subnet,
  edns_client_subnet_asn,
  edns_client_subnet_asn_organisation,
  edns_client_subnet_country,
  edns_ext_error,
  labels,
  ip_resp_frag,
  proc_time,
  server_location,
  pcap_file,
  query_f_tc,
  query_f_ra,
  query_f_ad,
  pub_resolver,
  req_len,
  res_len,
  tcp_hs_rtt,
  ip_req_df,
  ip_res_df,
  server;
}