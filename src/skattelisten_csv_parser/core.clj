(ns skattelisten-csv-parser.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [clojure.data.json :as json])
  (:gen-class))

(defn create-company
  [splitted-line]
  {:csv (get splitted-line 0)
   :name (get splitted-line 1)
   :se (get splitted-line 2)
   :income-year (get splitted-line 3)
   :company-type (get splitted-line 5)
   :taxable-income (get splitted-line 8)
   :deficit (get splitted-line 9)
   :corporate-tax (get splitted-line 10)})

(defn -main [& args]
  (let [[input-path output-path] args]
    (with-open [writer (io/writer output-path)]
      (doseq [t (->> input-path
                     io/reader
                     line-seq
                     (drop 1)
                     (pmap #(-> %
                                (str/split #",")
                                create-company
                                json/write-str
                                (str "\n"))))]
        (.write writer t)))))
