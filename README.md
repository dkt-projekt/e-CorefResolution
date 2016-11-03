# e-CorefResolution
This service links mentions of entities in input. It analyses the input as a whole and constructs chains of words or phrases that refer to the same entity (usually at the start of a chain). For example, in the sentence 'Angela Merkel is currently the chancellor. She may retire next year though.', the 'She' refers to 'Angela Merkel'. Typically, in the NER step, 'Angela Merkel' will be recognized (depending on the model used), but 'She' will not be recognised as an entity. This service links the two and annotates the mention of an already recognized entity with the URI of that entity, as can be seen in the example output below.

## Endpoint
http://api.digitale-kuratierung.de/api/e-nlp/CoreferenceResolution

## Input
The available input parameters are:

`language`: Currently, only English (`en`) is supported 

`informat`: The usual input formats as supported by NIF (http://persistence.uni-leipzig.org/nlp2rdf/specification/api.html).

**PLEASE NOTE** that this endpoint relies on entities that were recognised in an earlier step. Therefore, it makes no sense to supply plain text as input format (it will work, but will only return the input text in a NIF format), or supply input NIF that has no annotations for entities (it will only return the input).

## Output
```
@prefix dktnif: <http://dkt.dfki.de/ontologies/nif#> .
@prefix dbo:   <http://dbpedia.org/ontology/> .
@prefix rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
@prefix owl:   <http://www.w3.org/2002/07/owl#> .
@prefix xsd:   <http://www.w3.org/2001/XMLSchema#> .
@prefix itsrdf: <http://www.w3.org/2005/11/its/rdf#> .
@prefix nif:   <http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#> .
@prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#> .

<http://dkt.dfki.de/documents/#char=43,46>
        a                     nif:RFC5147String , nif:String ;
        nif:anchorOf          "She"^^xsd:string ;
        nif:beginIndex        "43"^^xsd:nonNegativeInteger ;
        nif:endIndex          "46"^^xsd:nonNegativeInteger ;
        nif:referenceContext  <http://dkt.dfki.de/documents/#char=0,78> ;
        owl:sameAs            "http://dkt.dfki.de/documents/#char=0,13"^^xsd:string .

<http://dkt.dfki.de/documents/#char=0,78>
        a               nif:Context , nif:String , nif:RFC5147String ;
        nif:beginIndex  "0"^^xsd:nonNegativeInteger ;
        nif:endIndex    "78"^^xsd:nonNegativeInteger ;
        nif:isString    "Angela Merkel is currently the chancellor. She will probably retire next year."^^xsd:string .

<http://dkt.dfki.de/documents/#char=0,13>
        a                     nif:String , nif:RFC5147String ;
        dbo:birthDate         "1954-07-17"^^xsd:date ;
        nif:anchorOf          "Angela Merkel"^^xsd:string ;
        nif:beginIndex        "0"^^xsd:nonNegativeInteger ;
        nif:endIndex          "13"^^xsd:nonNegativeInteger ;
        nif:referenceContext  <http://dkt.dfki.de/documents/#char=0,78> ;
        itsrdf:taClassRef     dbo:Person ;
        itsrdf:taIdentRef     <http://dbpedia.org/resource/Angela_Merkel> .
```



## cURL example
```
curl -X POST  -d "@prefix dktnif: <http://dkt.dfki.de/ontologies/nif#> .
@prefix dbo:   <http://dbpedia.org/ontology/> .
@prefix rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
@prefix xsd:   <http://www.w3.org/2001/XMLSchema#> .
@prefix itsrdf: <http://www.w3.org/2005/11/its/rdf#> .
@prefix nif:   <http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#> .
@prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#> .

<http://dkt.dfki.de/documents/#char=0,78>
        a               nif:RFC5147String , nif:String , nif:Context ;
        nif:beginIndex  "0"^^xsd:nonNegativeInteger ;
        nif:endIndex    "78"^^xsd:nonNegativeInteger ;
        nif:isString    "Angela Merkel is currently the chancellor. She will probably retire next year."^^xsd:string .

<http://dkt.dfki.de/documents/#char=0,13>
        a                     nif:RFC5147String , nif:String ;
        dbo:birthDate         "1954-07-17"^^xsd:date ;
        nif:anchorOf          "Angela Merkel"^^xsd:string ;
        nif:beginIndex        "0"^^xsd:nonNegativeInteger ;
        nif:endIndex          "13"^^xsd:nonNegativeInteger ;
        nif:referenceContext  <http://dkt.dfki.de/documents/#char=0,78> ;
        itsrdf:taClassRef     dbo:Person ;
        itsrdf:taIdentRef     <http://dbpedia.org/resource/Angela_Merkel> ." "http://api.digitale-kuratierung.de/api/e-nlp/CoreferenceResolution?language=en&informat=turtle"
```



