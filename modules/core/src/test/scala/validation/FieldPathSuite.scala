package jap.fields

import DefaultAccumulateVM._
import ValidationError._

class FieldPathSuite extends munit.FunSuite {
  test("FieldPath.name") {
    assertEquals(FieldPath.Root.name, "root")
    assertEquals(FieldPath("1", "2", "3").name, "3")
  }

  test("FieldPath.raw") {
    assertEquals(FieldPath.raw("a.b.c"), FieldPath(List("a", "b", "c")))
  }

  List(
    FieldPath.Root           -> "root",
    FieldPath("1", "2", "3") -> "1.2.3",
  ).foreach { case (field, expected) =>
    test(s"FieldPath.full/toString - $expected") {
      assertEquals(field.full, expected)
      assertEquals(field.toString, expected)
    }
  }

  List(
    (FieldPath("user", "username"), "name", FieldPath("user", "name")),
    (FieldPath.Root, "name", FieldPath("name")),
  ).foreach { case (path, name, expected) =>
    test(s"FieldPath.named: ${path.full} named ${name}") {
      assertEquals(path.named("name"), expected)
    }
  }
}
