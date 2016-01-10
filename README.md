# cljs.js error message test

Observe `cljs/eval[-str]` and `cljs/compile-str` evaluate an imbalanced `let` binding: `(fn [] (let [x 7 y] (prn y)))`.

`cljs/eval-str` prints expected error:

```clj
{:message "Could not eval eval-str", :data {:tag :cljs/analysis-error}, :cause #error {:message "bindings must be vector of even number of elements at line 1 ", :data {:file nil, :line 1, :column 8, :tag :cljs/analysis-error}}}
```

whereas `cljs/compile-str` throws:

```
Uncaught Error: No method in multimethod 'cljs.compiler/emit*' for dispatch value:
cljs$core$throw_no_method_error @ core.cljs:9611
cljs.core.MultiFn.call.G__10698__2 @ core.cljs:9626
cljs.core.MultiFn.call.G__10698 @ core.cljs:9613
cljs$compiler$emit @ compiler.cljc:170
(anonymous function) @ js.cljs:630
cljs$js$compile_str_STAR__$_compile_loop @ js.cljs:630
cljs$js$compile_str_STAR_ @ js.cljs:603
cljs.js.compile_str.cljs$core$IFn$_invoke$arity$5 @ js.cljs:674
cljs$js$compile_str @ js.cljs:644
(anonymous function) @ core.cljs:28
```
