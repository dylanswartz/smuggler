(ns smuggler.core
  (:use [clojure.string :only (split-lines)])
  (:gen-class))

(defn read-data-file
	[filename]
	(split-lines (slurp filename)))

(defn -main
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (if (= (count args) 0)
      ((println "Usage: lein run <path to data file>")
       (System/exit 0)))
  (def filename (first args))
  (println "Hello, World!"))
