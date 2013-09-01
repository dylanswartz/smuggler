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
  [line] (def tokens (split line #"\s+"))
  (struct doll (nth tokens 0) (read-string (nth tokens 1)) (read-string (nth tokens 2))))

(defn parse-dolls [data] [{:name "sally", :weight 4, :value 50} {:name "babe", :weight 30, :value 10}])

(defn -main
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (if (= (count args) 0)
      ((println "Usage: lein run <path to data file>")
       (System/exit 0)))
  (def filename (first args))
  (println "Hello, World!"))
