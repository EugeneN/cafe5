(ns cafe5.protocols.feedback)


(defprotocol IFeedbackAdaptor
  "Feedback adaptor is an interface which allows a program to send messages to
   users. Each particular UI must implement own feedback adaptor."
  (say     [fb msg] "info messages")
  (shout   [fb msg] "warning messages")
  (scream  [fb msg] "error messages")
  (whisper [fb msg] "debud messages")
  (murmur  [fb msg] "plain messages"))