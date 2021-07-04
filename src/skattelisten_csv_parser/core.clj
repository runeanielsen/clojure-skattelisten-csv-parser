(ns skattelisten-csv-parser.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [cheshire.core :as json])
  (:gen-class))

(defn create-company
  [splitted-line]
  {:csv (splitted-line 0)
   :name (splitted-line 1)
   :se (splitted-line 2)
   :incomeYear (splitted-line 3)
   :companyType (splitted-line 5)
   :taxableIncome (splitted-line 8)
   :deficit (splitted-line 9)
   :corporateTax (splitted-line 10)})

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
