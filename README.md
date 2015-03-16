MPMAP
=====

##Maritime Piracy Mapping Analysis Portal

**MPMAP is a web application and toolkit for filtering, mapping, analyzing, and exporting a dataset of approximately 6500 maritime piracy incidents dating back to 1993.**

MPMAP is a part of the Mapping Maritime Piracy research initiative with faculty and student contributors at the University of Tennessee, Knoxville and the University of Amsterdam. [mappingpiracy.net](http://mappingpiracy.net)

The project hosted in this repository can be viewed at: [mpmap.mappingpiracy.net](http://mappingpiracy.net)

###Version 0.25
MPMAP is following a loose versioning scheme and is currently at iteration 0.25.

###MPMAP uses the following tools and frameworks:
- Web framework: [Java Play Framework 2.3.6](https://www.playframework.com/)
- Database: [PostgreSQL](http://www.postgresql.org/) with:
  - [PostGIS](http://postgis.net/)
  - [MyBatis 3](https://github.com/mybatis/mybatis-3)
- Front-end framework: [AngularJS](https://angularjs.org/) with:
  - [Angular Leaflet Directive](https://github.com/tombatossals/angular-leaflet-directive)
  - [Angular Bootstrap](https://github.com/angular-ui/bootstrap)
  - [Angular UI/UI-Layout](https://github.com/angular-ui/ui-layout)
  - [Bootstrap](https://github.com/twbs/bootstrap)
  - [ngModal](https://github.com/adamalbrecht/ngModal)
- Mapping: [LeafletJS](https://github.com/Leaflet/Leaflet)
- Graphing/Visualization: 
  - [D3.js](https://github.com/mbostock/d3)
  - [nvD3.js](http://nvd3.org/)
- Dependency Management
  - [WebJars](https://github.com/webjars)

Front-end directory/file structure roughly follows this example: https://scotch.io/tutorials/angularjs-best-practices-directory-structure

###Related Repositories
- [MPMAP-Data](https://github.com/mappingpiracy/mpmap-data) hosts some useful raw data files, shape files, SQL scripts, and parsing scripts used for this project.

###Contributors
- [Alex Klibisz, University of Tennessee](http://alexklibisz.github.io)
