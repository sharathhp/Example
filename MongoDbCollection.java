package com.cerner.review.tracker.connection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;


/**
 * <p>
 * Establishing database connectivity and Fetching the database details, also fetching Associate Details from the
 * mongodb
 * </p>
 * <p>
 * Copyright &copy; 2018 Cerner Corporation
 * </p>
 *
 * @author Sharath HP
 */
public class MongoDbCollection
{
    /**
     * Returns the associates review details from db. This value returned can be {@code null} or empty based on the
     * associates selection.
     * 
     * @param filterKey
     *            accepts key for which the value needs to be filtered, This value cannot {@code null} or empty
     * @param filterValue
     *            accepts key for which the value needs to be filtered, This value cannot {@code null} or empty
     * @return Returns the selected associates details based on input values filtered
     */
    public DBCursor getMongoDbCollection(String filterKey, String filterValue)
    {
        if (filterKey == null || filterKey.isEmpty() || filterValue == null || filterValue.isEmpty())
        {
            return null;
        }
        MongoDBSingleton database = MongoDBSingleton.getInstance();
        DB db = database.getMongodb();
        DBCollection associateDetailsCollection = db.getCollection("AssociateDetails"); //$NON-NLS-1$
        BasicDBObject whereClause = new BasicDBObject();
        whereClause.put(filterKey, filterValue);
        return associateDetailsCollection.find(whereClause);
    }
}
