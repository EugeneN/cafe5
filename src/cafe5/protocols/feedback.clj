(ns cafe5.protocols.feedback)
(use 'colorize.core)


(defprotocol IFeedback
  "Feedback adaptor is an interface which allows a program to send messages to
   users. Each particular UI must implement own feedback adaptor."
  (say     [fb msg] "info messages")
  (shout   [fb msg] "warning messages")
  (scream  [fb msg] "error messages")
  (whisper [fb msg] "debud messages")
  (murmur  [fb msg] "plain messages"))

(defn- just-say [msg] (println (cyan "|> " (default msg))))

(extend-type nil
  IFeedback
  (say     [_ msg] (just-say msg))
  (shout   [_ msg] (just-say msg))
  (scream  [_ msg] (just-say msg))
  (whisper [_ msg] (just-say msg))
  (murmur  [_ msg] (just-say msg)))