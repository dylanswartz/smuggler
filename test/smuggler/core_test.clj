(ns smuggler.core-test
  (:require [clojure.test :refer :all]
            [smuggler.core :refer :all]))

(def testdoll (struct doll "sally" 4 50))
(def testdoll2 (struct doll "babe" 30 10))

(def sample-dolls [{:name "luke", :weight 9, :value 150}
                   {:name "anthony", :weight 13, :value 35}
                   {:name "candice", :weight 153, :value 200}
                   {:name "dorothy", :weight 50, :value 160}
                   {:name "puppy", :weight 15, :value 60}
                   {:name "thomas", :weight 68, :value 45}
                   {:name "randal", :weight 27, :value 60}
                   {:name "april", :weight 39, :value 40}
                   {:name "nancy", :weight 23, :value 30}
                   {:name "bonnie", :weight 52, :value 10}
                   {:name "marc", :weight 11, :value 70}
                   {:name "kate", :weight 32, :value 30}
                   {:name "tbone", :weight 24, :value 15}
                   {:name "tranny", :weight 48, :value 10}
                   {:name "uma", :weight 73, :value 40}
                   {:name "grumpkin", :weight 42, :value 70}
                   {:name "dusty", :weight 43, :value 75}
                   {:name "grumpy", :weight 22, :value 80}
                   {:name "eddie", :weight 7, :value 20}
                   {:name "tory", :weight 18, :value 12}
                   {:name "sally", :weight 4, :value 50}
                   {:name "babe", :weight 30, :value 10}])

(def best-dolls [0 1 2 3 4 6 10 15 16 17 18 20])

(testing "it should read a datafile"
	(deftest reads-data-file
		(is (= ["luke 9 150"] (read-data-file "test/smuggler/test.txt")))))

(testing "it should parse dolls"
	(deftest parses-doll
		(is (= testdoll (parse-doll "sally 4 50"))))
	(deftest parses-multiple-dolls
    	(def testdolls [testdoll testdoll2])
    	(is (= testdolls (parse-dolls ["sally 4 50" "babe 30 10"]))))
	(deftest parses-no-dolls
    	(is (= 0 (count (parse-dolls []))))))

(testing "it should maximize street-value"
  (deftest puts-correct-dolls-in-handbag
    (let [[street-value dolls-in-bag] (put-in-handbag sample-dolls 21 400)]
          (is (= best-dolls dolls-in-bag))
    )))