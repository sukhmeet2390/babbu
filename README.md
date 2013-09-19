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

-----------------

- activity typically represents a screen
- all activity states are controlled by Activity Manager


Activity Lifecycle -
- all the roads in get through onResume()
- all the roads out get through onPause()
- actcity is the main visible UI building block

- Intent represents a event/ message that is used to glue main building blocks like activities and services , broadcast recievers content providers
-- intent passes messages to invoke new activity
-- intent can be explicit or implicit
--- explicit means that we want to open a particular msg  in the GMail
--- implicit intent means that system resolves what to do with it.

- while changing the layout android by defualt destoys the previous activity and crates the new activity withe landscape layout.

-- download courseware for more resources

-Services
--code that runs in background but its running in the same UI thread
-- Services have a simple lifecycle of Start-> Running -> Destroyed
-- audio and video service can be paused
-- services can be bound / unbound

- Content Providers
-- share data between apps
-- Simple CRUD application

- BroadCast recievers
-- pub-sub ,
-- register to event , like sms recieved, battery low,
-- my app wakes up on its recieve()

-> Services are Sync(Game) , while BroadCast recivers are async(battery low)
=> HierachyViewer
- extract styles
- extract strings
- extract colors
- create themes
- adb logcat (play with )
- for buttons you can have smaller lines of code
- add jtwitter libarary to your project libraries

- avoid long running tasks on main thread
- Threads shoudn't update anything thats UI
- Async Task
- trace
-- how to look into DDMS in Idea
 -- traceview yanba.trace
- Service
-- after u build a java class for a building block, u need to update the manifest
- glue the service with Intents
- to parse from xml we need an infater
-- separate thread for long runnning tasks
- refetch the timeline
- intent Service
-- onHandleIntent
-- Refersh service, created it and then updated and then destoryed it itself
- application class
- preference screen
-- Preference Activity
-- TabActivity
-- ListActivity
- getBaseContext() vs getApplicationContext() vs this
-- perferences get saved in EditText wheere-> data/data/app/shared_pref/ folder
-- u can't read it on live mobile, permission denied
-- refresh the twitter object when preferenecs change
- preferences return a string only
- BroadCast reviever
- what to do when system boots up => start a Service
- broadcast recievers have a context , and we use context to start the service
- context can be application context or activity context (which usually is same as application context)
- recievers
-- do register via Intent filters
-- do subscribe
- standard intents are avaliable in android.intent docs
-- add additional permissions for the intent filter in the android.manifest
- adb shell ps
-- give and take permissions
--- define the protectionLevel and when u use implicit  intents u might need them
- if u need to call by its name  u need to provide the intent filter then u can use it


-- Intents can be used to start an activity , or service , and also a broadcast
-- attach small amount of data to intents, only primitive data
-- avoid radio opening
- AlarmManager do it in BootReciever
- use approtpirate permission for your services used in alarmManager
- declare your own events
- location manager