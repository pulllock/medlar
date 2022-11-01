# Solr

- version: `9.0`

# 安装、配置、启动Solr

本地测试使用docker安装solr，命令如下：

```
docker network create solr
docker pull solr:9.0.0
docker run -d --name solr --net solr -p 8983:8983 solr:9.0.0
```

访问Solr Admin Console：

- 浏览器访问：`http://localhost:8983`

# Schema

```
<schema>
  <types>
    <fieldType>
  <fields>
    <field>
  <copyField>
  <dynamicField>
  <similarity>
  <uniqueKey>
</schema>
```

# Fields

```
<field name="price" type="float" default="0.0" indexed="true" stored="true"/>
```

| Property                 | Required | Default |
| ------------------------ | -------- | ------- |
| name                     | required | none    |
| type                     | required | none    |
| default                  | optional | none    |
| indexed                  | optional | true    |
| stored                   | optional | true    |
| docValues                | optional | false   |
| sortMissingFirst         | optional | false   |
| sortMissingLast          | optional | false   |
| multiValued              | optional | false   |
| uninvertible             | optional | true    |
| omitNorms                | optional | *       |
| omitTermFreqAndPositions | optional | *       |
| omitPositions            | optional | *       |
| termVectors              | optional | false   |
| termPositions            | optional | false   |
| termOffsets              | optional | false   |
| termPayloads             | optional | false   |
| required                 | optional | false   |
| useDocValuesAsStored     | optional | true    |
| large                    | optional | false   |

## Field Types

```xml
<fieldType name="text_general" class="solr.TextField" positionIncrementGap="100"> 
  <analyzer type="index"> 
    <tokenizer class="solr.StandardTokenizerFactory"/>
    <filter class="solr.StopFilterFactory" ignoreCase="true" words="stopwords.txt" />
    <!-- in this example, we will only use synonyms at query time
    <filter class="solr.SynonymFilterFactory" synonyms="index_synonyms.txt" ignoreCase="true" expand="false"/>
    -->
    <filter class="solr.LowerCaseFilterFactory"/>
  </analyzer>
  <analyzer type="query">
    <tokenizer class="solr.StandardTokenizerFactory"/>
    <filter class="solr.StopFilterFactory" ignoreCase="true" words="stopwords.txt" />
    <filter class="solr.SynonymFilterFactory" synonyms="synonyms.txt" ignoreCase="true" expand="true"/>
    <filter class="solr.LowerCaseFilterFactory"/>
  </analyzer>
</fieldType>
```

| Property                  | Required | Default      | Values                                                   |
| ------------------------- | -------- | ------------ | -------------------------------------------------------- |
| name                      | required | none         |                                                          |
| class                     | required | none         |                                                          |
| positionIncrementGap      | optional | none         |                                                          |
| autoGeneratePhraseQueries | optional | true         |                                                          |
| synonymQueryStyle         | optional | as_same_term | - as_same_term<br />- pick_best<br />- as_distinct_terms |
| enableGraphQueries        | optional | true         |                                                          |
| indexed                   | optional | true         |                                                          |
| stored                    | optional | true         |                                                          |
| docValues                 | optional | false        |                                                          |
| docValuesFormat           | optional | none         |                                                          |
| docValuesFormat           | optional | none         |                                                          |
| sortMissingFirst          | optional | false        |                                                          |
| sortMissingLast           | optional | false        |                                                          |
| multiValued               | optional | false        |                                                          |
| uninvertible              | optional | true         |                                                          |
| omitNorms                 | optional | *            |                                                          |
| omitTermFreqAndPositions  | optional | *            |                                                          |
| omitPositions             | optional | *            |                                                          |
| termVectors               | optional | false        |                                                          |
| termPositions             | optional | false        |                                                          |
| termOffsets               | optional | false        |                                                          |
| termPayloads              | optional | false        |                                                          |
| required                  | optional | false        |                                                          |
| useDocValuesAsStored      | optional | true         |                                                          |
| large                     | optional | false        |                                                          |

### Field Types

| Class                               |
|:----------------------------------- |
| BBoxField                           |
| BinaryField                         |
| BoolField                           |
| CollationField                      |
| CurrencyFieldType                   |
| DateRangeField                      |
| DenseVectorField                    |
| DatePointField                      |
| DoublePointField                    |
| ExternalFileField                   |
| EnumFieldType                       |
| FloatPointField                     |
| ICUCollationField                   |
| IntPointField                       |
| LatLonPointSpatialField             |
| LongPointField                      |
| NestPathField                       |
| PointType                           |
| PreAnalyzedField                    |
| RandomSortField                     |
| RankField                           |
| RptWithGeometrySpatialField         |
| SortableTextField                   |
| SpatialRecursivePrefixTreeFieldType |
| StrField                            |
| TextField                           |
| UUIDField                           |

# Analysis

- analyzers
- tokenizers
- filters

## Tokenizers

| Tokenizer                                                 | name               | class                                   | arguments                             |
| --------------------------------------------------------- | ------------------ | --------------------------------------- | ------------------------------------- |
| Standard Tokenizer                                        | standard           | solr.StandardTokenizerFactory           | - maxTokenLength                      |
| Classic Tokenizer                                         | classic            | solr.ClassicTokenizerFactory            | - maxTokenLength                      |
| Keyword Tokenizer                                         | keyword            | solr.KeywordTokenizerFactory            |                                       |
| Letter Tokenizer                                          | letter             | solr.LetterTokenizerFactory             |                                       |
| Lower Case Tokenizer                                      | lowercase          | solr.LowerCaseTokenizerFactory          |                                       |
| N-Gram Tokenizer                                          | nGram              | solr.NGramTokenizerFactory              | - minGramSize<br/>- maxGramSize       |
| Edge N-Gram Tokenizer                                     | edgeNGram          | solr.EdgeNGramTokenizerFactory          | - minGramSize<br/>- maxGramSize       |
| ICU Tokenizer                                             | icu                | solr.ICUTokenizerFactory                | - rulefile                            |
| Path Hierarchy Tokenizer                                  | text_path          | solr.PathHierarchyTokenizerFactory      | - delimiter<br/>- replace             |
| Regular Expression Pattern Tokenizer                      | pattern            | solr.PatternTokenizerFactory            | - pattern<br/>- group                 |
| Simplified Regular Expression Pattern Tokenizer           | simplePattern      | solr.SimplePatternTokenizerFactory      | - pattern<br/>- maxDeterminizedStates |
| Simplified Regular Expression Pattern Splitting Tokenizer | simplePatternSplit | solr.SimplePatternSplitTokenizerFactory | - pattern<br/>- maxDeterminizedStates |
| UAX29 URL Email Tokenizer                                 | uax29URLEmail      | solr.UAX29URLEmailTokenizerFactory      | - maxTokenLength                      |
| White Space Tokenizer                                     | whitespace         | solr.WhitespaceTokenizerFactory         | - rule                                |

## Filters

| Filter                         | name                  | class                                                   | arguments                                                                                                                                                                                                                              |
| ------------------------------ | --------------------- | ------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| ASCII Folding Filter           | asciiFolding          | solr.ASCIIFoldingFilterFactory                          | - preserveOriginal                                                                                                                                                                                                                     |
| Beider-Morse Filter            | beiderMorse           | solr.BeiderMorseFilterFactory                           | - nameType<br/>- ruleType<br/>- concat<br/>- languageSet                                                                                                                                                                               |
| Classic Filter                 | classic               | solr.ClassicFilterFactory                               |                                                                                                                                                                                                                                        |
| Common Grams Filter            | commonGrams           | solr.CommonGramsFilterFactory                           | - words<br/>- format<br/>- ignoreCase                                                                                                                                                                                                  |
| Common Grams Query Filter      | commonGramsQuery      | solr.CommonGramsQueryFilterFactory                      | - words<br/>- format<br/>- ignoreCase                                                                                                                                                                                                  |
| Collation Key Filter           |                       |                                                         |                                                                                                                                                                                                                                        |
| Daitch-Mokotoff Soundex Filter | daitchMokotoffSoundex | solr.DaitchMokotoffSoundexFilterFactory                 | - inject                                                                                                                                                                                                                               |
| Double Metaphone Filter        | doubleMetaphone       | solr.DoubleMetaphoneFilterFactory                       | - inject<br/>- maxCodeLength                                                                                                                                                                                                           |
| Delimited Boost Filter         | delimitedBoost        | solr.DelimitedBoostTokenFilterFactory                   | - delimiter                                                                                                                                                                                                                            |
| Edge N-Gram Filter             | edgeNGram             | solr.EdgeNGramFilterFactory                             | - minGramSize<br/>- maxGramSize<br/>- preserveOriginal                                                                                                                                                                                 |
| English Minimal Stem Filter    | englishMinimalStem    | solr.EnglishMinimalStemFilterFactory                    |                                                                                                                                                                                                                                        |
| English Possessive Filter      | englishPossessive     | solr.EnglishPossessiveFilterFactory                     |                                                                                                                                                                                                                                        |
| Fingerprint Filter             | fingerprint           | solr.FingerprintFilterFactory                           | - separator<br/>- maxOutputTokenSize                                                                                                                                                                                                   |
| Flatten Graph Filter           | flattenGraph          | solr.FlattenGraphFilterFactory                          |                                                                                                                                                                                                                                        |
| Hunspell Stem Filter           | hunspellStem          | solr.HunspellStemFilterFactory                          | - dictionary<br/>- affix<br/>- ignoreCase<br/>- strictAffixParsing                                                                                                                                                                     |
| Hyphenated Words Filter        | hyphenatedWords       | solr.HyphenatedWordsFilterFactory                       |                                                                                                                                                                                                                                        |
| ICU Folding Filter             | icuFolding            | solr.ICUFoldingFilterFactory                            | - filter                                                                                                                                                                                                                               |
| ICU Normalizer 2 Filter        | icuNormalizer2        | solr.ICUNormalizer2FilterFactory                        | - form<br/>- mode<br/>- filter                                                                                                                                                                                                         |
| ICU Transform Filter           | icuTransform          | solr.ICUTransformFilterFactory                          | - id                                                                                                                                                                                                                                   |
| Keep Word Filter               | keepWord              | solr.KeepWordFilterFactory                              | - words<br/>- ignoreCase                                                                                                                                                                                                               |
| KStem Filter                   | kStem                 | solr.KStemFilterFactory                                 |                                                                                                                                                                                                                                        |
| Length Filter                  | length                | solr.LengthFilterFactory                                | - min<br/>- max                                                                                                                                                                                                                        |
| Limit Token Count Filter       | limitTokenCount       | solr.LimitTokenCountFilterFactory                       | - +<br/>- consumeAllTokens                                                                                                                                                                                                             |
| Limit Token Offset Filter      | limitTokenOffset      | solr.LimitTokenOffsetFilterFactory                      | - maxStartOffset<br/>- consumeAllTokens                                                                                                                                                                                                |
| Limit Token Position Filter    | limitTokenPosition    | solr.LimitTokenPositionFilterFactory                    | - maxTokenPosition<br/>- consumeAllTokens                                                                                                                                                                                              |
| Lower Case Filter              | lowercase             | solr.LowerCaseFilterFactory                             |                                                                                                                                                                                                                                        |
| Managed Stop Filter            | managedStop           | solr.ManagedStopFilterFactory                           | - managed                                                                                                                                                                                                                              |
| Managed Synonym Filter         |                       | solr.ManagedSynonymFilterFactory                        |                                                                                                                                                                                                                                        |
| Managed Synonym Graph Filter   | managedSynonymGraph   | solr.ManagedSynonymGraphFilterFactory                   | - managed                                                                                                                                                                                                                              |
| MinHash Filter                 |                       | org.apache.lucene.analysis.minhash.MinHashFilterFactory | - hashCount<br/>- bucketCount<br/>- hashSetSize<br/>- withRotation                                                                                                                                                                     |
| N-Gram Filter                  | nGram                 | N-Gram Filter                                           | - minGramSize<br/>- maxGramSize<br/>- preserveOriginal                                                                                                                                                                                 |
| Numeric Payload Token Filter   | numericPayload        | solr.NumericPayloadTokenFilterFactory                   | - payload<br/>- typeMatch                                                                                                                                                                                                              |
| Pattern Replace Filter         | patternReplace        | solr.PatternReplaceFilterFactory                        | - pattern<br/>- replacement<br/>- replace                                                                                                                                                                                              |
| Phonetic Filter                | phonetic              | solr.PhoneticFilterFactory                              | - encoder<br/>- inject<br/>- maxCodeLength                                                                                                                                                                                             |
| Porter Stem Filter             | porterStem            | solr.PorterStemFilterFactory                            |                                                                                                                                                                                                                                        |
| Protected Term Filter          | protectedTerm         | solr.ProtectedTermFilterFactory                         | - protected<br/>- wrappedFilters<br/>- ignoreCase                                                                                                                                                                                      |
| Remove Duplicates Token Filter | removeDuplicates      | solr.RemoveDuplicatesTokenFilterFactory                 |                                                                                                                                                                                                                                        |
| Reversed Wildcard Filter       | reversedWildcard      | solr.ReversedWildcardFilterFactory                      | - withOriginal<br/>- maxPosAsterisk<br/>- maxPosQuestion<br/>- maxFractionAsterisk<br/>- minTrailing                                                                                                                                   |
| Shingle Filter                 | shingle               | solr.ShingleFilterFactory                               | - minShingleSize<br/>- maxShingleSize<br/>- outputUnigrams<br/>- outputUnigramsIfNoShingles<br/>- tokenSeparator<br/>- fillerToken                                                                                                     |
| Snowball Porter Stemmer Filter | snowballPorter        | solr.SnowballPorterFilterFactory                        | - language<br/>- protected                                                                                                                                                                                                             |
| Stop Filter                    | stop                  | solr.StopFilterFactory                                  | - words<br/>- format<br/>- ignoreCase                                                                                                                                                                                                  |
| Suggest Stop Filter            | suggestStop           | solr.SuggestStopFilterFactory                           | - words<br/>- format<br/>- ignoreCase                                                                                                                                                                                                  |
| Synonym Filter                 |                       | solr.SynonymFilterFactory                               |                                                                                                                                                                                                                                        |
| Synonym Graph Filter           | synonymGraph          | solr.SynonymGraphFilterFactory                          | - synonyms<br/>- ignoreCase<br/>- expand<br/>- format<br/>- tokenizerFactory<br/>- analyzer                                                                                                                                            |
| Token Offset Payload Filter    | tokenOffsetPayload    | solr.TokenOffsetPayloadTokenFilterFactory               |                                                                                                                                                                                                                                        |
| Trim Filter                    | trim                  | solr.TrimFilterFactory                                  |                                                                                                                                                                                                                                        |
| Type As Payload Filter         | typeAsPayload         | solr.TypeAsPayloadTokenFilterFactory                    |                                                                                                                                                                                                                                        |
| Type As Synonym Filter         | typeAsSynonym         | solr.TypeAsSynonymFilterFactory                         | - prefix                                                                                                                                                                                                                               |
| Type Token Filter              | typeToken             | solr.TypeTokenFilterFactory                             | - types<br/>- useWhitelist                                                                                                                                                                                                             |
| Word Delimiter Filter          |                       | solr.WordDelimiterFilterFactory                         |                                                                                                                                                                                                                                        |
| Word Delimiter Graph Filter    | wordDelimiterGraph    | solr.WordDelimiterGraphFilterFactory                    | - generateWordParts<br/>- generateNumberParts<br/>- splitOnCaseChange<br/>- splitOnNumerics<br/>- catenateWords<br/>- catenateNumbers<br/>- catenateAll<br/>- preserveOriginal<br/>- protected<br/>- stemEnglishPossessive<br/>- types |

## Char Filter

| Char Filter                     | name           | class                                | arguments                      |
| ------------------------------- | -------------- | ------------------------------------ | ------------------------------ |
| MappingCharFilterFactory        | mapping        | solr.MappingCharFilterFactory        | - mapping                      |
| HTMLStripCharFilterFactory      | htmlStrip      | solr.HTMLStripCharFilterFactory      |                                |
| ICUNormalizer2CharFilterFactory | icuNormalizer2 | solr.ICUNormalizer2CharFilterFactory | - form<br/>- mode<br/>- filter |
| PatternReplaceCharFilterFactory | patternReplace | solr.PatternReplaceCharFilterFactory | - pattern<br/>- replacement    |

# Query

## Common Query Parameters

| patameter             | name         | values                |
| --------------------- | ------------ | --------------------- |
| defType               |              | - lucene<br/>- dismax |
| sort                  |              |                       |
| start                 |              |                       |
| rows                  |              |                       |
| canCancel             |              |                       |
| queryUUID             |              |                       |
| fq                    | Filter Query |                       |
| cache                 | cache Local  |                       |
| fl                    | Field List   |                       |
| debug                 |              |                       |
| explainOther          |              |                       |
| timeAllowed           |              |                       |
| segmentTerminateEarly |              |                       |
| omitHeader            |              |                       |
| wt                    |              |                       |
| logParamsList         |              |                       |
| echoParams            |              |                       |
| minExactCount         |              |                       |

## Standard Query Parser

### Parameters

| patameter | name                     | values             |
| --------- | ------------------------ | ------------------ |
| q         | query                    |                    |
| q.op      | operator                 | - AND<br/>- OR     |
| df        | default searchable field |                    |
| sow       | split on whitespace      | - true<br/>- false |

- Terms
  - Single Term: `"test" or "hello"`
  - Phrase: `"hello dolly"`
- Fields: `title:"The Right Way" AND text:go`
- Wildcard Searches
  - `？` single character: `te?t`
  - `*` 0 or more characters: `test*`
- Regular Expression Searches: `/regular expression/`
- Fuzzy Searches:
  - `~` at the end of a `Single word Term`.
  - `roma~1` can specify the maximum number of edits allowed, the value is between 0 and 2.
- Proximity Searches: `~` at the end of a `Phrase`, example: `"jakarta apache"~10`
- Range Searches:
  - `mod_date:[20220101 TO 20221010]` inclusive range queries.
  - `title:{Aida TO Carmen}` exclusive range queries.
- Boosting a Term: `^`, example: `jakarta^4 apache` or `"jakarta apache"^4 "Apache Lucene"`
- Constant Score with `^=`
- Boolean Operators
  - `OR` or `||`
  - `AND` or `&&`
  - `+`, example: `+jakarta lucene` documents must contain "jakarta" and may contain "lucene"
  - `-`, example: `"jakarta apache" -"Apache Lucene"` documents that contain "jakarta apache" but not "Apache Lucene"
  - `NOT` or `!`
- Grouping: `(jakarta OR apache) AND website`
- Field Grouping: `title:(+return +"pink panther")`
- Escaping Special Characters: `\`
- Comments in Queries: `jakarta apache" /* this is a comment in the middle of a normal query string */ OR jakarta`

## DisMax Query Parser

DisMax(Maximum Disjunction)

### Parameters

| patameter | name                 | values |
| --------- | -------------------- | ------ |
| q         | query                |        |
| q.alt     |                      |        |
| qf        | Query Fields         |        |
| mm        | Minimum Should Match |        |
| pf        | Phrase Fields        |        |
| ps        | Phrase Slop          |        |
| qs        | Query Phrase Slop    |        |
| tie       | Tie Breaker          |        |
| bq        | Boost Query          |        |
| bf        | Boost Functions      |        |

## Extended DisMax (eDisMax) Query Parser

### Parameters

| patameter          | name                 | values |
| ------------------ | -------------------- | ------ |
| q                  | query                |        |
| q.alt              |                      |        |
| qf                 | Query Fields         |        |
| mm                 | Minimum Should Match |        |
| mm.autoRelax       |                      |        |
| pf                 | Phrase Fields        |        |
| pf2                |                      |        |
| pf3                |                      |        |
| ps                 | Phrase Slop          |        |
| ps2                |                      |        |
| ps3                |                      |        |
| qs                 | Query Phrase Slop    |        |
| tie                | Tie Breaker          |        |
| bq                 | Boost Query          |        |
| bf                 | Boost Functions      |        |
| sow                | Split on whitespace  |        |
| boost              |                      |        |
| lowercaseOperators |                      |        |
| stopwords          |                      |        |
| uf                 |                      |        |
|                    |                      |        |
|                    |                      |        |
|                    |                      |        |
|                    |                      |        |
|                    |                      |        |
