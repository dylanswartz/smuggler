(ns smuggler.core-test
  (:require [clojure.test :refer :all]
            [smuggler.core :refer :all]))

(def testdoll (struct doll "sally" 4 50))
(def testdoll2 (struct doll "babe" 30 10))

(testing "it should read a datafile"
	(deftest reads-data-file
		(is (= ["luke 9 150"] (read-data-file "test/smuggler/test.txt")))))

(testing "it should parse dolls"
	(deftest parses-doll
		(is (= testdoll (parse-doll "sally 4 50"))))
	(deftest parses-multiple-dolls
    	(def testdolls [testdoll testdoll2])
    	(is (= testdolls (parse-dolls ["sally 4 50" "babe 30 10"])))))