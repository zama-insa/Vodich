## Vodich App

ESB Qualification Scenario Launcher

[![Build Status](http://46.101.112.94:8080/job/VodichJob/badge/icon)](http://46.101.112.94:8080/job/VodichJob)

## Configure the whole thing

#### Application dependencies:

* [apache-activemq-5.13.0](http://activemq.apache.org/activemq-5130-release.html)
* [ultraesb-2.3.0](https://github.com/zama-insa/ultraesb-2.3.0) (take our customized version, it's better!)
* [kibana-4.2.2](https://www.elastic.co/downloads/past-releases/kibana-4-2.2)

## Launch the app

* Launch ActiveMQ: `[activemq_folder]/bin/activemq start` 
  - Verify that the activemq process actually exists. The web console is available at [http://localhost:8161](http://localhost:8161/admin/)
* Launch UltraESB: `[ultraesb-folder]/bin/ultraesb.sh` 
* Launch Kibana: `[kibana-folder]/bin/kibana.sh` 
* Finally, Launch Tomcat server. For both Vodich and Producer.

## Troubleshoot

1. If Kinaba visualizations don't work ([http://localhost:9200](http://localhost:9200)), it means that the elasticsearch data folder is locate elsewhere, not Vodich/data. You need to find that folder and copy Kibana data there (located under Vodich/data/main/index/nodes/0/indices/.kibana)
