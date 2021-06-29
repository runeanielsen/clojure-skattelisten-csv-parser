(ns skattelisten-csv-parser.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [cheshire.core :as json])
  (:gen-class))

(defn create-company
  [splitted-line]
  {:csv (get splitted-line 0)
   :name (get splitted-line 1)
   :se (get splitted-line 2)
   :incomeYear (get splitted-line 3)
   :companyType (get splitted-line 5)
   :taxableIncome (get splitted-line 8)
   :deficit (get splitted-line 9)
   :corporateTax (get splitted-line 10)})

(defn -main
  [& args]
  (let [[input-path output-path] args]
    (json/generate-stream
     (->> input-path
          io/reader
          line-seq
          (drop 1)
          (map #(->> (str/split % #",")
                     create-company)))
     (io/writer output-path))))
