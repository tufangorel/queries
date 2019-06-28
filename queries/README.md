# Cars Polygons Query REST API

REST endpoint that allows to query cars and polygons with specific features.

### Run with
 Run: `queries > mvn spring-boot:run`

### Tech Stack
Java 8<br/>
Spring Boot<br/>
Spring Data JPA<br/>
Spring Scheduler<br/>
Hibernate<br/>
H2 in memory db<br/>
Maven<br/>
Google Gson<br/>
springfox-swagger2<br/>
Hystrix<br/>
Docker<br/>
 
### Database Tables
create table city_vehicle (id integer not null, fuel double, latitude double, longitude double, model varchar(255), number_plate varchar(255), vin varchar(255), primary key (id)) <br/>
create table point (id bigint not null, latitude double, longitude double, polygon_id varchar(255), primary key (id)) <br/>
create table polygon (id varchar(255) not null, name varchar(255), primary key (id)) <br/>

### Database Tables Definitions
city_vehicle = Stores car details information fetched from http://localhost:3000/vehicles/Stuttgart <br/>
polygon = Stores polygon information fetched from https://gist.githubusercontent.com/codeofsumit/6540cdb245bd14c33b486b7981981b7b/raw/73ebda86c32923e203b2a8e61615da3e5f1695a2/polygons.json <br/>
point = Join table for point-polygon pairs <br/>

### Prerequisites

* Before running this Spring Boot application it is required to pull and run the docker image listed below : <br/><br/>

* docker pull car2godeveloper/api-for-coding-challenge <br/>
* docker run -d -p 3000:3000 car2godeveloper/api-for-coding-challenge <br/>

### Rest Api Design Decisions

* When the Spring Boot application starts after the application context initialized https://gist.githubusercontent.com/codeofsumit/6540cdb245bd14c33b486b7981981b7b/raw/73ebda86c32923e203b2a8e61615da3e5f1695a2/polygons.json  is queried to fetch polygon data. <br/>

* Stores the polygon data in H2 database and does not query remote service again. <br/>

* CarQueryJob contiously fetches car information from another locally running rest api to gather car location information in every 60 seconds. <br/>

### Requirements
* Query cars with polygon id they are in. Note : found single polygon id is returned. <br/>
* Query polygons include the VIN of cars currently in them. Note : All the cars inside current polygon is returned. <br/>

### Query cars

Method : HTTP.GET <br/>
URL : http://localhost:8080/cars/retrieveCars <br/>

Input Test Data : No input just fire the request. "polygonId" field contains the id of current car's polygon. <br/>

Request : 
<pre>
{
}
</pre><br/>
Response : 

HTTP response code 200 <br/>
<pre>
[
  {
    "id": 1,
    "locationId": null,
    "vin": "1GCGK34J9YD4JVCFW",
    "numberPlate": "S-IQ7213",
    "position": {
      "latitude": 48.75430489282382,
      "longitude": 9.256467604055967
    },
    "fuel": 0.1,
    "model": "SMART_42_ELECTRIC",
    "polygonId": "58a58bd185979b5415f39d78"
  }
]
</pre><br/>

### Query polygons

Method : HTTP.GET <br/>
URL : http://localhost:8080/polygons/retrievePolygons <br/>

Input Test Data : No input just fire the request. "vinsOfCars" field contains car vin information for the specific polygon. <br/>

Request :
<pre>
{
}
</pre><br/>
Response : 

HTTP response code 200 <br/>
<pre>
[
 {
    "id": "58a58c05766d51540f779bc4",
    "updatedAt": null,
    "createdAt": null,
    "v": null,
    "name": "111",
    "cityId": null,
    "legacyId": null,
    "type": null,
    "geoFeatures": null,
    "timedOptions": null,
    "geometry": {
      "type": null,
      "coordinates": [
        [
          [
            48.7173283425,
            9.1506641217
          ],
          [
            48.7180471865,
            9.15531458734
          ],
          [
            48.7207196233,
            9.15331317462
          ],
          [
            48.7243944113,
            9.15322734393
          ],
          [
            48.7225447239,
            9.16249720636
          ],
          [
            48.721600962,
            9.16829092591
          ],
          [
            48.7211384938,
            9.1709087619
          ],
          [
            48.721671688,
            9.17230351059
          ],
          [
            48.7215253911,
            9.1727541217
          ],
          [
            48.718313,
            9.170581
          ],
          [
            48.7162806971,
            9.16904237036
          ],
          [
            48.7168328373,
            9.16346075528
          ],
          [
            48.7166771061,
            9.1548750661
          ],
          [
            48.7173283425,
            9.1506641217
          ]
        ]
      ]
    },
    "version": null,
    "vinsOfCars": [
      "1B3LB28C198UXF3XS",
      "1GAZGYCL8BNS2VAA2"
    ]
  }
]
</pre><br/>
