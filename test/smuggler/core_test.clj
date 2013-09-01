(ns smuggler.core-test
  (:require [clojure.test :refer :all]
            [smuggler.core :refer :all]))

(def testdoll (struct doll "sally" 4 50))

(testing "it should read a datafile"
	(deftest reads-data-file
		(is (= ["luke 9 150"] (read-data-file "test/smuggler/test.txt")))))
