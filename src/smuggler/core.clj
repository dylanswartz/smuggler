(ns smuggler.core
  (:use [clojure.string :only (split-lines)])
  (:use [clojure.string :only (split)])
  (:gen-class))

; define a data structure for the doll data
(defstruct doll :name :weight :value)

(defn read-data-file
	[filename]
	(split-lines (slurp filename)))

(defn parse-doll 
  [line] 
  (def tokens (split line #"\s+"))
  (struct doll (nth tokens 0) (read-string (nth tokens 1)) (read-string (nth tokens 2))))

(defn parse-dolls 
  [data] 
  (map parse-doll data))

(defn put-in-handbag
  "Pack dolls into handbag while maximizing street value"
  [doll-coll index-val handbag-weight]
  [123 [0 1 2 3 4 6 10 15 16 17 18 20]]
  )

(defn -main
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (if (= (count args) 0)
      ((println "Usage: lein run <path to data file>")
       (System/exit 0)))
  (def filename (first args))
  (println "Hello, World!"))
