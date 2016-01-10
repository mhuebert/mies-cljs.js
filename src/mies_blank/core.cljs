(ns mies-blank.core
  (:require [cljs.js :as cljs]))

;; (defonce conn
;;   (repl/connect "http://localhost:9000/repl"))

(enable-console-print!)

(do

  (def test-compiler-opts {:eval       cljs/js-eval
                           :context    :expr
                           :source-map true})

  (def test-cstate (cljs/empty-state))
  (def test-sample "(fn [] (let [x 7 y] (prn y)))")

  (prn "Attempting to evaluate buggy code: " test-sample)

  (defn cb [s {:keys [value error]}]
    (prn "callback from " s ": ")
    (if error (prn "error: " error)
              (prn "value: " value))
    (when (= s :compile-str)
      (prn "Evaluating result of cljs/compile-str: " (js/eval value))))

  (cljs/eval-str test-cstate test-sample "eval-str" test-compiler-opts (partial cb :eval-str))
  (cljs/compile-str test-cstate test-sample "compile-str" test-compiler-opts (partial cb :compile-str)))