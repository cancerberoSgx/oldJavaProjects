use at you own risk. this is perhaps the cheaper way of accessing dom object from java code, but
you will have to deal with dom imp`lementation inconsistensis by you r self.  

when you touch object of these types you are touching pure html dom objects of the browser (with all the well known incopatibilities) 

here we have adaptations of w3c java language bindings of dom2-core, 
dom2-events, dom2-html. All these classes shoud make references only to classes 
inside this package and not to org.w3c.dom types.

the purpuse of these interfaces is to wrap directly to html dom objects
when executing in javascript. This means that they are not implemented in java: 
the objects that implement/wrap these interfaces are pure browser html dom objects.
a java program shouln'd be asociated to a  java2js.morphs.html.dom type.

Sometimes we need classes to wrap attributes
like document.body, window.navigator, etc. 

a part of these files where taken from the original w3c htmldom java api. 
but only the "well supported" methods are uncommented. te purpuse of w3c 
 api is for htmldom java implementators usage. But in our case it is a guide to  
 make a secure java api that wraps a compatible subset of the html dom api

there are other interfaces that make reference to those objects
that the w3c html dom ref doesn't define like Window, 
Navigator (window.navigator obj), etc (others could be Frame, Location, History)...
we create them and will be poblating