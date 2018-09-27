# decathlon-result-calculator
A project to calculate the results of a Decathlon competition

These are the main ability of project

    Ability to read decathlon results file
    Ability to calculate points and write rankings to file

This project was developed with spring boot. Java 8 is required. No database was used.

Here is example usage.


    Reader reader = new CSVReader("results.csv");
    ResultReader resultReader = new DecathlonResultReader(reader);
    ResultCalculator resultCalculator = new DecathlonResultCalculator(resultReader);
    List<Ranking> rankings = resultCalculator.calculateRankings();
   
    RankingsXMLWriter writer = new DecathlonRankingsXMLWriter("rankings.xml", rankings);
    writer.writeRankingsXML();
   
   
   
