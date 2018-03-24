# test-compare-endpoints

A test harness in bash scripts to bulk compare responses provided by two rest services.

The bash scripts will iterate over an input file of ids.  For each id, two rest endpoints will be called.  The results are compared and any differences logged.  

A test stub servicing two rest endpoints is provided (using java, spring boot, and camel, which is a bit overkill).  
