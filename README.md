DATABASE

- define columns for db
- SQLLiteOpenHelper to open db and operate on that
- avoid exposing it to oustide world
- use the helper and on your data object create CRUD operations

Adapters

- to connect to your data sources (database / list / array etc)
--- List ===> Adapter ====> Cursor

-- Use ViewBinder to bind the values from the database to the view with us to inject in between.