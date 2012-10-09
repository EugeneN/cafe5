(ns cafe5.adaptors.plain_javascript_file
  (:use cafe5.protocols.adaptor))

(def get-adaptor []
  (reify IAdaptor
    (match         [adaptor ctx] true)
    (get_deps      [adaptor ctx recipe_deps] "Return module deps")
    (harvest       [adaptor ctx] "Compile module")
    (last-modified [adaptor ctx] "Get newest modification date")
    (harvest-tests [adaptor ctx] "Compile tests")
    (run-tests     [adaptor ctx] "Run tests, return true or list of failedtests (?)")
    (process-deps  [adaptor ctx] "Process and install module's deps if any")
    (hello         [adaptor ctx greet] (println "ZZZ HEllo" greet))))