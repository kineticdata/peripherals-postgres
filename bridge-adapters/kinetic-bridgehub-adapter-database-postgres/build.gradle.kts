plugins {
  java
  `maven-publish`
}

repositories {
  mavenLocal()
  maven {
    url = uri("https://s3.amazonaws.com/maven-repo-public-kineticdata.com/releases")
  }

  maven {
    url = uri("s3://maven-repo-private-kineticdata.com/releases")
    authentication {
      create<AwsImAuthentication>("awsIm")
    }

  }

  maven {
    url = uri("https://s3.amazonaws.com/maven-repo-public-kineticdata.com/snapshots")
  }

  maven {
    url = uri("s3://maven-repo-private-kineticdata.com/snapshots")
    authentication {
      create<AwsImAuthentication>("awsIm")
    }
  }

  maven {
    url = uri("https://repo.maven.apache.org/maven2/")
  }

  maven {
    url = uri("https://repo.springsource.org/release/")
  }

}

dependencies {
  implementation("com.kineticdata.bridges.adapter:kinetic-bridgehub-adapter:1.2.1-SNAPSHOT")
  implementation("org.slf4j:slf4j-api:1.7.10")
  implementation("org.postgresql:postgresql:42.3.7")
  implementation("com.kineticdata.bridgehub.adapter:kinetic-bridgehub-adapter-database:1.0.5-SNAPSHOT")
}

group = "com.kineticdata.bridgehub.adapter.sql"
version = "1.1.2-SNAPSHOT"
description = "kinetic-bridgehub-adapter-database-postgres"
java.sourceCompatibility = JavaVersion.VERSION_1_8

publishing {
  publications.create<MavenPublication>("maven") {
    from(components["java"])
  }
  repositories {
    maven {
      val releasesUrl = uri("s3://maven-repo-private-kineticdata.com/releases")
      val snapshotsUrl = uri("s3://maven-repo-private-kineticdata.com/snapshots")
      url = if (version.toString().endsWith("SNAPSHOT")) snapshotsUrl else releasesUrl
      authentication {
        create<AwsImAuthentication>("awsIm")
      }
    }
  }
}

tasks.withType<JavaCompile>() {
  options.encoding = "UTF-8"
}
