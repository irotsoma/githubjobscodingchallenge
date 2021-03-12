This application loads data from the GitHub Jobs API at 
https://jobs.github.com/api and returns the percentage of jobs in a list of
locations that reference a given programming language.

### Challenges:

The primary challenge that arose during this exercise was related to escaping
characters in the API calls. I eventually found that both the URIComponentsBuilder
and the Spring Rest Template were escaping characters so any languages
with special characters like C# or any locations with spaces were getting
double escaped. Found that returning a URI from the URIComponentsBuilder instead of
a string causes it not to escape.

### Most Proud Of:

The simplicity and efficiency. Unfortunately, since the API has so few records
in it, there was no need to make it able to handle large amounts of data by
default.

### Least Proud Of:

The UI is pretty simple. I didn't realize there would be so little data, so 
the way it's designed it shows all records, even the 0% ones. If I had it over
to design, I would have created a separate model to pass to the mustache template
instead of reusing the JPA object with a transitive variable for the percentage added.

### Tradeoffs:
The biggest tradeoff is related to the API calls. I decided to make separate calls for
each group of records instead of making one call for each location and do the lanugage
search locally. This is the right way to do it for an API IMHO, but since there 
was so little data it ended up making the total query time much, much longer.

### Next areas of focus:

There needs to be an ajax status bar for loading the data from the API which
also means making that process async from the controller.

As noted above, the object sent to the mustache template should be separate
from the JPA object allowing for suppressing the 0 jobs records.