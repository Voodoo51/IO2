package com.IO2.Gradebook.utils;

import com.IO2.Gradebook.models.Grade;

import java.util.List;

public interface GradeViewStrategy<T> {
    T build(List<Grade> grades);
}