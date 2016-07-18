(ns boost-bin.csv-test
  (:require [speclj.core :refer :all]
            [boost-bin.csv :as under-test]
            [clj-fakes.core :as f]
            [clojure.data.csv :as c]))

(def a-csv-file "derpyderpyderpy")
(def a-csv-file-as-string "flerpnderpndoo")
(def a-csv [[:a :b :c] [1 2 3] [4 5 6]])

(describe
  "as vector-map"
  (it "return csv as vector map]"
      (f/with-fakes
        (f/patch! #'slurp (f/fake [[a-csv-file] a-csv-file-as-string]))
        (f/patch! #'c/read-csv (f/fake [[a-csv-file-as-string] a-csv]))

        (should= [1 4] (:a (under-test/as-vector-map a-csv-file)))
        (should= [2 5] (:b (under-test/as-vector-map a-csv-file)))
        (should= [3 6] (:c (under-test/as-vector-map a-csv-file))))))
