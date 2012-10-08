(ns cafe5.protocols.adaptor)

(defprotocol IAdaptor
  "Adaptor protocol is an interface to work with modules"
  (match         [adaptor ctx] "Test if a module can be compiled with this adaptor")
  (get_deps      [adaptor ctx recipe_deps] "Return module deps")
  (harvest       [adaptor ctx] "Compile module")
  (last-modified [adaptor ctx] "Get newest modification date")
  (harvest-tests [adaptor ctx] "Compile tests")
  (run-tests     [adaptor ctx] "Run tests, return true or list of failedtests (?)")
  (process-deps  [adaptor ctx] "Process and install module's deps if any"))