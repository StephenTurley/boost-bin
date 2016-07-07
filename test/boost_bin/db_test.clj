(ns boost-bin.db_test
  (:require [speclj.core :refer :all]
            [boost-bin.db :as underTest]
            [boost-bin.csv :as csv]
            [clojure.java.io :as io]
            [clj-fakes.core :as f :include-macros true]))

(def test-data (io/resource "test/testdata.csv"))
(def vector-map {:derp [:foo :bar]})

(f/with-fakes
  (describe "save data log"
    (it "should return the csv as a map"
    (f/patch! #'boost-bin.csv/as-vector-map (f/fake [[f/any?] vector-map]))
      (should= vector-map
            (underTest/save-data-log {:tempfile test-data})))))