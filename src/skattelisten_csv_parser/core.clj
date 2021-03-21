(ns skattelisten-csv-parser.core
  (:require [clojure.java.io :as io])
  (:require [clojure.data.json :as json])
  (:require [clojure.string :as str])
  (:gen-class))

(defn create-company
  [splitted-line]
  {:csv (nth splitted-line 0)
   :name (nth splitted-line 1)
   :se (nth splitted-line 2)
   :incomeYear (nth splitted-line 3)
   :companyType (nth splitted-line 5)
   :taxableIncome (nth splitted-line 8)
   :deficit (nth splitted-line 9)
   :corporateTax (nth splitted-line 10)})

(defn convert
  [csv-line]
  (->> (str/split csv-line #",")
       create-company))

(defn -main
  [& args]
  (def input-path (nth args 0))
  (def output-path (nth args 1))
  (->> input-path
       io/reader
       line-seq
       (drop 1)
       (map convert)
       json/write-str
       (spit output-path)))
