(ns boost-bin.db_test
  (:require [speclj.core :refer :all]
            [boost-bin.db :as under-test]
            [boost-bin.csv :as csv]
            [clojure.java.io :as io]
            [clj-fakes.core :as f]))

(def test-data (io/resource "test/testdata.csv"))
(def vector-map {:derp [:foo :bar]})

(describe
  "save data log"
  (it "should return the csv as a map"
      (f/with-fakes
        (f/patch! #'csv/as-vector-map (f/fake [[test-data] vector-map]))

        (should= vector-map
                 (under-test/save-data-log {:tempfile test-data})))))