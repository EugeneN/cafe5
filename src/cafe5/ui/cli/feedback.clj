(ns cafe5.ui.cli.feedback)
"Concrete implementation of the feedback protocol for ui.cli module"
(use 'colorize.core
     'cafe5.protocols.feedback)

(def DEBUG_PREFIX "DEBUG:")

(defn- make-fb [prefix colour]
  (fn [msg]
    (println (color colour (if (nil? prefix) "" (str prefix " "))
           (default (apply str (interpose " " (if (coll? msg) msg [msg]))))))))

(defn- get-color [color nocolor]
  (if nocolor :default color))

(defn get-fb [prefix nocolor quiet panic_mode]
  (let [say     (if-not quiet
                  (make-fb prefix (get-color :green nocolor)) (fn [msg] nil))

        shout   (if-not quiet
                  (make-fb prefix (get-color :yellow nocolor)) (fn [msg] nil))

        scream  (make-fb prefix (get-color :red nocolor))

        whisper (if panic_mode
                  (make-fb  (str DEBUG_PREFIX prefix) (get-color :red nocolor))
                  (fn [msg] nil))

        murmur  (if-not quiet
                  (make-fb nil (get-color :green nocolor)) (fn [msg] nil))]

    (reify IFeedback
      (say     [_ msg] (say msg))
      (shout   [_ msg] (shout msg))
      (scream  [_ msg] (scream msg))
      (whisper [_ msg] (whisper msg))
      (murmur  [_ msg] (murmur msg)))))


