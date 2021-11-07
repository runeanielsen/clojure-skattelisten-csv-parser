(ns skattelisten-csv-parser.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [clojure.data.json :as json])
  (:gen-class))

(defn create-company
  [splitted-line]
  {:csv (splitted-line 0)
   :name (splitted-line 1)
   :se (splitted-line 2)
   :income-year (splitted-line 3)
   :company-type (splitted-line 5)
   :taxable-income (splitted-line 8)
   :deficit (splitted-line 9)
   :corporate-tax (splitted-line 10)})

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
        (.write writer t))))
  (shutdown-agents))
