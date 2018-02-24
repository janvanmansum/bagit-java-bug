package nl.knaw.dans;

import gov.loc.repository.bagit.domain.Bag;
import gov.loc.repository.bagit.exceptions.CorruptChecksumException;
import gov.loc.repository.bagit.exceptions.VerificationException;
import gov.loc.repository.bagit.reader.BagReader;
import gov.loc.repository.bagit.verify.BagVerifier;

import java.awt.*;
import java.nio.file.Paths;

public class Test {

  public static void main(String[] args) throws Exception {
    BagReader reader = new BagReader();
    BagVerifier verifier = new BagVerifier();

    Bag bag = reader.read(Paths.get("src/main/resources/incorrect-tagfile-checksum"));

    for (int i = 0; i < 1000; ++i) {
      try {
        verifier.isValid(bag, false);
      } catch(CorruptChecksumException e) {
        //System.err.println("OK");
      } catch(VerificationException e) {
        System.err.println("FAIL");
      }
    }
    verifier.close();
    System.err.println("DONE");

  }
}
