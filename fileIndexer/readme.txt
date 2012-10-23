 indexador de archivos orientado a varios medios de almacenaje como cds, hds, tapes, webdavs, etc...

utiliza lucene para indexar

la idea es que el usuario pueda crear repositorios y en cada uno de ellos indicar carpetas que se indexarán recursivamente. 

cada archivo indexado estará asociado a un objeto Media y se compondrá de "ruta", "nombre de archivo", "extensión" y contenido entre otras props

Los objetos la media.buildPath( junto con la ruta absoluta de un archivo 

para cada tipo de repositorio