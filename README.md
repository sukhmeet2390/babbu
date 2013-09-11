DATABASE

- define columns for db
- SQLLiteOpenHelper to open db and operate on that
- avoid exposing it to oustide world
- use the helper and on your data object create CRUD operations

Adapters

- to connect to your data sources (database / list / array etc)
--- List ===> Adapter ====> Cursor

-- Use ViewBinder to bind the values from the database to the view with us to inject in between.

-- the data doesn't refresh
-- so i need a broadCast reciever
-- the best is to start it when timelineActivity is in view, ie when onResume() and stop it in onPause()

-- tried to move TimeLineReciever seperately , breaks.. FIX that
-- u can also attach some data in Intents