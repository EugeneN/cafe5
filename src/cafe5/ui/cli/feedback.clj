(ns cafe5.ui.cli.feedback)
"Concrete implementation of the feedback protocol for ui.cli module"
(use 'colorize.core
     'cafe5.protocols.feedback)

(def DEBUG_PREFIX "DEBUG:")

(defn- make-fb [prefix colour]
  (fn [msg]
    (color colour (if (nil? prefix) "" (str prefix " "))
           (default (apply str (interpose " " (if (coll? msg) msg [msg])))))))

(defn- get-color [color nocolor]
  (if nocolor :default color))

(defn get-fb [prefix nocolor]
  (let [say     (make-fb prefix (get-color :green nocolor))
        shout   (make-fb  prefix (get-color :yellow nocolor))
        scream  (make-fb prefix (get-color :red nocolor))
        whisper (make-fb  (str DEBUG_PREFIX prefix) (get-color :red nocolor))
        murmur  (make-fb nil (get-color :green nocolor))]

    (reify cafe5.protocols.feedback.IFeedback
      (say     [_ msg] (say msg))
      (shout   [_ msg] (shout msg))
      (scream  [_ msg] (scream msg))
      (whisper [_ msg] (whisper msg))
      (murmur  [_ msg] (murmur msg)))))


