# Lucene

- version: `9.4.1`

# Query Classes

- TermQuery
  - Term
- BooleanQuery
  - SHOULD
  - MUST
  - FILTER
  - MUST NOT
- PhraseQuery
- MultiPhraseQuery
- PointRangeQuery
- PrefixQuery
- WildcardQuery
  - `*` 0 or more
  - `?` exactly one
- RegexpQuery
- FuzzyQuery

# Query Parser Syntax

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
- Boolean Operators
  - `OR` or `||`
  - `AND` or `&&`
  - `+`, example: `+jakarta lucene` documents must contain "jakarta" and may contain "lucene"
  - `-`, example: `"jakarta apache" -"Apache Lucene"` documents that contain "jakarta apache" but not "Apache Lucene"
  - `NOT` or `!`
- Grouping: `(jakarta OR apache) AND website`
- Field Grouping: `title:(+return +"pink panther")`
- Escaping Special Characters: `\`