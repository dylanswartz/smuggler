(ns smuggler.core
  (:use [clojure.string :only (split-lines)])
  (:gen-class))

(defn read-data-file
	[filename]
	(split-lines (slurp filename)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (println "Hello, World!"))
