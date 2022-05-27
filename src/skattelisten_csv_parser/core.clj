(ns skattelisten-csv-parser.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [clojure.data.json :as json])
  (:gen-class))

(defn splitted-csv-line->company
  [splitted-line]
  {:csv (splitted-line 0)
   :name (splitted-line 1)
   :se (splitted-line 2)
   :income-year (splitted-line 3)
   :company-type (splitted-line 5)
   :taxable-income (splitted-line 8)
   :deficit (splitted-line 9)
   :corporate-tax (splitted-line 10)})

(defn map->json-new-line [m]
  (str (json/write-str m :escape-unicode false) "\n"))

(defn split-csv-line [line]
  (str/split line #";"))

(def csv-line->company-json-new-line
  (comp
   map->json-new-line
   splitted-csv-line->company
   split-csv-line))

(defn pmap-csv-file [fn in-file out-file]
  (with-open [reader (io/reader in-file :buffer-size 4096)
              writer (io/writer out-file :buffer-size 4096)]
    (let [lines (drop 1 (line-seq reader))]
      (dorun (map #(.write writer %) (pmap fn lines))))))

(defn -main [& args]
  (let [[input-path output-path] args]
    (pmap-csv-file csv-line->company-json-new-line input-path output-path)))
