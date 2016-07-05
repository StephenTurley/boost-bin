(ns boost-bin.db_test
  (:require [speclj.core :refer :all]
            [boost-bin.db :as underTest]
            [clojure.java.io :as io]))

(describe "save data log"
          (it "should return the csv as a string"
              (should= (slurp (io/resource "test/testdata.csv"))
                       (underTest/save-data-log {:tempfile (io/resource "test/testdata.csv")}))))