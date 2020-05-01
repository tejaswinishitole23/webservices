# URL vs #URI ?

# http - how does it work ?

# html - mark up language
meant to be processed by a client application - browser.

## doctype
## html - root element

### head - document metadata
<title> - title of data
<metadata> - metadata such as keywords. headers.
<script> - script for interactive pages
<style> - css files for styles
<link> - directive indicating related docs. used for linking to style sheets.
<base> - indication of base uri for links that are relative. say base address for any html file mentioned in document.

## body - document data
most of the work happens and shown to users.

Important tags:
Headings -
```html
    <h1>Main heading<h1>
    <h2>Overview<h2>
    <h3>Objectives<h3>
    <h4>whatever<h4>
    <h5>whatever<h5>
    <h6>whatever<h6>
```
Text
Lists
Links
Tables
Images/Objects

##### important attributes for elements on a page:
id - to identify the element. used in scripting, styling. must be unique on the page.
class - identify items . class is shared. used to apply styles mostly.

##### block vs inline
block elements
- container for grouping
- may contain sub blocks
```html
    <div>
```
inline elements
- for text and other elements

```html
    <span>
```

span can't contain a div within it.

##### white space and other signs
generally ignored
```html
    <pre> - whitespace is respected.

    <br/> - explicit line break

    <hr/> - horizontal rule
```

char entities
    
```html
    &nbsp; non-breaking space-  space which can't be used for line breaks. put between 2 words and they will wrap together or they won't.
    
    &lt; - shows < sign.
    
    &gt; - show > sign. 
```


##### document elements.
```html
    <sup> - superscript
    <sub> - subscript
    <cite> - cite work by another
    <abbr> - abbreviation
    <acronym> - acronym
    <em>, <strong> - emphasis
    <code><samp> - indicates code block or program output
    <kbd><var> - keyboard input and code variables
    <blockquote><q> - block level or inline quote. specify a url also to show where it is coming from. may or may not work depending upon browser.
```

##### lists
underordered lists - bullet list
```html
    <ul>
```
ordered list - numberic or alpha
```html
    <ol>
```
definition list - term + definition
```html
    <dl>
```

##### links
anchors - act as either source or target for linking. 
```html
    <a name="TableOfContents"/>
    <a href="http://www.somewebsite.com/">My website</a>
```

linking to documents
linking within documents ( not just start of the page..)

##### tables
caption - table title
header - single row for headers
body - for data - multiple rows
footer - single row usually for total , summary etc

important points
rowspan, colspan

width, align, border, cell padding, cell spacing
no wrap

##### images and objects
```html
    <img src="./content/bed.jpg" alt="bed" height="250px" width="250px"/>
    <object data="./content/food_tests.pdf" height="250px" width="250px"/>
```