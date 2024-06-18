So as per the instructions, I copied most of the work from prototype-0.
When I try to run the cargo project, I recieve these errors:

```

error[E0433]: failed to resolve: use of undeclared crate or module `prototype_0`
 --> src\main.rs:4:5
  |
4 | use prototype_0::moves::{MoveError};
  |     ^^^^^^^^^^^ use of undeclared crate or module `prototype_0`

error[E0433]: failed to resolve: use of undeclared crate or module `prototype_0`
 --> src\main.rs:5:5
  |
5 | use prototype_0::moves::ranker::{RandomRanker, Ranker};
  |     ^^^^^^^^^^^ use of undeclared crate or module `prototype_0`

error[E0433]: failed to resolve: use of undeclared crate or module `prototype_0`
 --> src\main.rs:6:5
  |
6 | use prototype_0::types::{Board,Player,Move};
  |     ^^^^^^^^^^^ use of undeclared crate or module `prototype_0`

error[E0433]: failed to resolve: use of undeclared crate or module `prototype_0`
 --> src\main.rs:7:5
  |
7 | use prototype_0::validators::win_validator;
  |     ^^^^^^^^^^^ use of undeclared crate or module `prototype_0`

error[E0432]: unresolved import `regex_lite`
 --> src\main.rs:2:5
  |
2 | use regex_lite::Regex;
  |     ^^^^^^^^^^ use of undeclared crate or module `regex_lite`

warning: unnecessary parentheses around pattern
  --> src\main.rs:10:9
   |
10 |     let (move_error) = board.make_move(next_move, player);
   |         ^          ^
   |
   = note: `#[warn(unused_parens)]` on by default
help: remove these parentheses
   |
10 -     let (move_error) = board.make_move(next_move, player);
10 +     let move_error = board.make_move(next_move, player);
   |

Some errors have detailed explanations: E0432, E0433.
For more information about an error, try `rustc --explain E0432`.
warning: `prototype-12` (bin "prototype-12") generated 1 warning
error: could not compile `prototype-12` (bin "prototype-12") due to 5 previous errors; 1 warning emitted

```

I tried fixing these errors, but they just generated new errors.

```

error[E0433]: failed to resolve: use of undeclared crate or module `prototype_12`
 --> src\main.rs:4:5
  |
4 | use prototype_12::moves::ranker::{RandomRanker, Ranker};
  |     ^^^^^^^^^^^^ use of undeclared crate or module `prototype_12`

error[E0433]: failed to resolve: use of undeclared crate or module `prototype_12`
 --> src\main.rs:5:5
  |
5 | use prototype_12::moves::MoveError;
  |     ^^^^^^^^^^^^ use of undeclared crate or module `prototype_12`

error[E0433]: failed to resolve: use of undeclared crate or module `prototype_12`
 --> src\main.rs:6:5
  |
6 | use prototype_12::types::{Board, Move, Player};
  |     ^^^^^^^^^^^^ use of undeclared crate or module `prototype_12`

error[E0433]: failed to resolve: use of undeclared crate or module `prototype_12`
 --> src\main.rs:7:5
  |
7 | use prototype_12::validators::win_validator;
  |     ^^^^^^^^^^^^ use of undeclared crate or module `prototype_12`

error[E0432]: unresolved import `regex_lite`
 --> src\main.rs:1:5
  |
1 | use regex_lite::Regex;
  |     ^^^^^^^^^^ use of undeclared crate or module `regex_lite`

Some errors have detailed explanations: E0432, E0433.
For more information about an error, try `rustc --explain E0432`.
error: could not compile `prototype-12` (bin "prototype-12") due to 5 previous errors

```