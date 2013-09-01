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

(defn parse-int [s]
   (Integer. (re-find  #"\d+" s )))

(declare put-in-handbag-mem)

(defn put-in-handbag
  "Pack dolls into handbag while maximizing street value"
  [doll-coll index-val handbag-weight]
  (cond
    (< index-val 0) [0 []] ; there are no dolls
    (empty? doll-coll) [0 []] ; there are no dolls
    (= handbag-weight 0) [0 []] ; the handbag cannot carry any more weight
    :else
     (let [{doll-weight :weight doll-value :value} (get doll-coll index-val)]
      (if (> doll-weight handbag-weight) ; if the doll weighs too much
        ; try putting in previous doll 
        (put-in-handbag-mem doll-coll (dec index-val) handbag-weight)
        ; otherwise get the remaining handbag weight and continue
        (let [new-handbag-weight (- handbag-weight doll-weight)
          ; determine if doll maximizes street value
          [no-val no-vec :as no] (put-in-handbag-mem doll-coll (dec index-val) handbag-weight)
          [yes-val yes-vec :as yes] (put-in-handbag-mem doll-coll (dec index-val) new-handbag-weight)]
          ; if the target street value is increased 
          (if (> (+ yes-val doll-value) no-val) ;
            ; add this doll to the handbag
            [(+ yes-val doll-value) (conj yes-vec index-val)]
            ; don't put it in the handbag
            no))))))

(def put-in-handbag-mem (memoize put-in-handbag))

(defn -main
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (if (< (count args) 2)
      ((println "Usage: lein run <path to data file>")
       (System/exit 0)))
  (def filename (first args))
  (def max-weight (parse-int (second args)))

  ; Create a vector of dolls fom the doll data like so:
  ;[{:name luke, :weight 9, :value 150} 
  ; {:name anthony, :weight 13, :value 35}
  ; {:name candice, :weight 153, :value 200} ... ]
  (def dolls (vec (parse-dolls (read-data-file filename))))
  (let [[street-value dolls-in-bag] (put-in-handbag dolls(-> dolls count dec) max-weight)]
    (println "packed dolls: \n")
    ; use dorun to get around map's laziness
    (dorun (map #(println (get dolls %)) (reverse dolls-in-bag)))
    (println "\ntotal street-value:" street-value)
    (println "total weight:" (reduce + (map (comp :weight dolls) dolls-in-bag)))))
