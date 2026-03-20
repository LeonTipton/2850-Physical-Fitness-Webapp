# READ THIS DOCUMENT REGARDING HOW TABLES AND CLASSES ARE SET UP

ID tables are: 
```kotlin
// IntIdTables
DistancesTable
GymworkTable
SportsTable
SwimTable
UserTable

// IdTable<Int> -- inheriting primary key from one of the above
BiometricsTable
LoginTable

// CompositeIdTable
RecommendationsTable
```
These are all mapped to a class instance of the same name that explicitly denotes datatypes

---

Generic tables are:
```kotlin
DistancesEquipTable
DistancesIntensityTable
DistancesTypeTable
GymworkMusclesTable
SportsMuscleTables
SwimEquipTable
SwimStrokesTable
```
These do not have their own classes, instead their datatypes are explicitly typed in their related ID table class. 
For instance: `DistancesEquipTable` has variable types in the `Distances` class.

All foreign keys have `ReferenceOption.CASCADE` to prevent records existing without a linked primary key 