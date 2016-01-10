(ns mies-blank.core
  (:require [cljs.js :as cljs]))

;; (defonce conn
;;   (repl/connect "http://localhost:9000/repl"))

(enable-console-print!)

(println "Hello world!")

(do

  (def test-compiler-opts {:eval       cljs/js-eval
                           :context    :expr
                           :source-map true})

  (def test-cstate (cljs/empty-state))

  (def test-sample "(fn [] (let [x 7 y] (prn y)))")

  (defn cb [s {:keys [value error] :as result}]
    (prn "finished " s ": ")
    (if error (prn "error: " error)
              (prn "value: " value))
    (when (= s :compile-str)
      (js/eval value)))

  (cljs/eval-str test-cstate test-sample "eval-str" test-compiler-opts (partial cb :eval-str))

  (cljs/compile-str test-cstate test-sample "compile-str" test-compiler-opts (partial cb :compile-str)))