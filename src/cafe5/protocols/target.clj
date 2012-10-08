(ns cafe5.protocols.target)

(defprotocol ITarget
  "Target protocol is an interface to targets."
  (run [target ctx] "Run the target"))