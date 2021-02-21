import * as app1 from "./test";

// @ts-ignore
import app2 = require("./test");
import {App} from "./test";

var a1: app1.App.SomeClass  = new app1.App.SomeClass();
var a2: app1.App.OtherClass = new app1.App.OtherClass();

var b1: app2.App.SomeClass  = new app2.App.SomeClass();
var b2: app2.App.OtherClass = new app2.App.OtherClass();

var c1: App.SomeClass  = new App.SomeClass();
var c2: App.OtherClass = new App.OtherClass();
