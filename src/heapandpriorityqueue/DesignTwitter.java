package heapandpriorityqueue;

import java.util.*;

/*
 * Design Twitter
 *
 * Implement a simplified version of Twitter which allows users to post tweets, follow/unfollow each other, and view the
 * 10 most recent tweets within their own news feed.
 *
 * Users and tweets are uniquely identified by their IDs (integers).
 *
 * Implement the following methods:
 *
 * - Twitter() Initializes the twitter object.
 * - void postTweet(int userId, int tweetId) Publish a new tweet with ID tweetId by the user userId. You may assume that
 * each tweetId is unique.
 * - List<Integer> getNewsFeed(int userId) Fetches at most the 10 most recent tweet IDs in the user's news feed. Each
 * item must be posted by users who the user is following or by the user themself. Tweets IDs should be ordered from
 * most recent to least recent.
 * - void follow(int followerId, int followeeId) The user with ID followerId follows the user with ID followeeId.
 * - void unfollow(int followerId, int followeeId) The user with ID followerId unfollows the user with ID followeeId.
 *
 * Example 1:
 *
 * Input:
 * ["Twitter", "postTweet", [1, 10], "postTweet", [2, 20], "getNewsFeed", [1], "getNewsFeed", [2], "follow", [1, 2],
 * "getNewsFeed", [1], "getNewsFeed", [2], "unfollow", [1, 2], "getNewsFeed", [1]]
 * Output:
 * [null, null, null, [10], [20], null, [20, 10], [20], null, [10]]
 * Explanation:
 * Twitter twitter = new Twitter();
 * twitter.postTweet(1, 10); // User 1 posts a new tweet with id = 10.
 * twitter.postTweet(2, 20); // User 2 posts a new tweet with id = 20.
 * twitter.getNewsFeed(1);   // User 1's news feed should only contain their own tweets -> [10].
 * twitter.getNewsFeed(2);   // User 2's news feed should only contain their own tweets -> [20].
 * twitter.follow(1, 2);     // User 1 follows user 2.
 * twitter.getNewsFeed(1);   // User 1's news feed should contain both tweets from user 1 and user 2 -> [20, 10].
 * twitter.getNewsFeed(2);   // User 2's news feed should still only contain their own tweets -> [20].
 * twitter.unfollow(1, 2);   // User 1 follows user 2.
 * twitter.getNewsFeed(1);   // User 1's news feed should only contain their own tweets -> [10].
 *
 * Constraints:
 *
 * 1. 1 <= userId, followerId, followeeId <= 100
 * 2. 0 <= tweetId <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(nlogn) time for each getNewsFeed() function call, O(1) time for the remaining
 * methods, and O((N * m) + (N * M) + n) space, where n is the number of followeeIds associated with the userId, m is
 * the maximum number of tweets by any user, N is the total number of userIds, and M is the maximum number of followees
 * for any user.
 */
class Twitter {

    private final HashMap<Integer, Set<Integer>> followerIdToFolloweeIds;
    private final HashMap<Integer, List<Tweet>> followerIdToTweets;
    private int count;

    public Twitter() {
        followerIdToFolloweeIds = new HashMap<>();
        followerIdToTweets = new HashMap<>();
        count = 0;
    }

    // Time complexity O(1)
    public void postTweet(int userId, int tweetId) {
        List<Tweet> tweets = followerIdToTweets.getOrDefault(userId, new LinkedList<>());
        if (tweets.size() == 10) {
            tweets.remove(0);
        }
        tweets.add(new Tweet(count, tweetId));
        followerIdToTweets.put(userId, tweets);
        count++;
    }

    // Time complexity O(n log(n)), where n is the number of followeeIds associated with a userId.
    public List<Integer> getNewsFeed(int userId) {

        // eligibleUserIds holds all userIds for which we can show Tweets of.
        Set<Integer> eligibleFolloweeIds = followerIdToFolloweeIds.getOrDefault(userId, new HashSet<>());
        Set<Integer> eligibleUserIds = new HashSet<>();
        eligibleUserIds.addAll(eligibleFolloweeIds);
        eligibleUserIds.add(userId);

        // Create heap that will always hold the most recent tweets of eligible user ids.
        PriorityQueue<TweetWithExtraInfo> mostRecentTweetsOfEligibleUserIdsMaxHeap = new PriorityQueue<>(
                (o1, o2) -> o2.tweet.count - o1.tweet.count // Comparator sorts tweets by count descending.
        );
        for (int eligibleUserId : eligibleUserIds) {
            List<Tweet> eligibleTweets = followerIdToTweets.getOrDefault(eligibleUserId, new LinkedList<>());
            if (!eligibleTweets.isEmpty()) {
                Tweet mostRecentEligibleTweet = eligibleTweets.get(eligibleTweets.size() - 1);
                mostRecentTweetsOfEligibleUserIdsMaxHeap.add(new TweetWithExtraInfo(mostRecentEligibleTweet, eligibleUserId, eligibleTweets.size() - 1));
            }
        }

        // Start crafting news feed.
        int newsFeedCountRemaining = 10;
        List<Integer> newsFeed = new ArrayList<>();
        while (newsFeedCountRemaining > 0 && !mostRecentTweetsOfEligibleUserIdsMaxHeap.isEmpty()) {
            // Remove most recent eligible tweet from heap.
            TweetWithExtraInfo mostRecentEligibleTweet = mostRecentTweetsOfEligibleUserIdsMaxHeap.poll();

            // A most recent tweet for the polled user id tweet is not there. We need to add the next most recent tweet back to the heap (if there is one).
            List<Tweet> eligibleTweetsForPolledUserId = followerIdToTweets.getOrDefault(mostRecentEligibleTweet.userId, new LinkedList<>());
            int nextIndex = mostRecentEligibleTweet.listIndex - 1;
            if (nextIndex >= 0) {
                Tweet nextTweetToAddForPolledUserId = eligibleTweetsForPolledUserId.get(mostRecentEligibleTweet.listIndex - 1);
                mostRecentTweetsOfEligibleUserIdsMaxHeap.add(
                        new TweetWithExtraInfo(
                                nextTweetToAddForPolledUserId,
                                mostRecentEligibleTweet.userId,
                                mostRecentEligibleTweet.listIndex - 1
                        )
                );
            }

            // Add news feed id and decrement counter.
            newsFeed.add(mostRecentEligibleTweet.tweet.id);
            newsFeedCountRemaining--;
        }

        return newsFeed;
    }

    // Time complexity O(1)
    public void follow(int followerId, int followeeId) {
        Set<Integer> followeeIds = followerIdToFolloweeIds.getOrDefault(followerId, new HashSet<>());
        followeeIds.add(followeeId);
        followerIdToFolloweeIds.put(followerId, followeeIds);
    }

    // Time complexity O(1)
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> followeeIds = followerIdToFolloweeIds.getOrDefault(followerId, new HashSet<>());
        followeeIds.remove(followeeId);
        followerIdToFolloweeIds.put(followerId, followeeIds);
    }

    static class Tweet {

        int count;
        int id;

        public Tweet(int count, int id) {
            this.count = count;
            this.id = id;
        }
    }

    static class TweetWithExtraInfo {

        Tweet tweet;
        int userId;
        int listIndex;

        public TweetWithExtraInfo(Tweet tweet, int userId, int listIndex) {
            this.tweet = tweet;
            this.userId = userId;
            this.listIndex = listIndex;
        }
    }
}

class DesignTwitterTester {

    public static void runExample1() {
        System.out.println("Example 1");
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 10);               // User 1 posts a new tweet with id = 10.
        twitter.postTweet(2, 20);               // User 2 posts a new tweet with id = 20.
        System.out.println(twitter.getNewsFeed(1));     // User 1's news feed should only contain their own tweets -> [10].
        System.out.println(twitter.getNewsFeed(2));     // User 2's news feed should only contain their own tweets -> [20].
        twitter.follow(1, 2);               // User 1 follows user 2.
        System.out.println(twitter.getNewsFeed(1));     // User 1's news feed should contain both tweets from user 1 and user 2 -> [20, 10].
        System.out.println(twitter.getNewsFeed(2));     // User 2's news feed should still only contain their own tweets -> [20].
        twitter.unfollow(1, 2);             // User 1 follows user 2.
        System.out.println(twitter.getNewsFeed(1));     // User 1's news feed should only contain their own tweets -> [10].
    }

    public static void runExample2() {
        System.out.println("Example 2");
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 101);
        twitter.postTweet(1, 102);
        twitter.follow(2, 1);
        System.out.println(twitter.getNewsFeed(2));     // User 2's news feed should be -> [102, 101]
        twitter.unfollow(2, 1);
        System.out.println(twitter.getNewsFeed(2));     // User 2's news feed should be -> []
    }

    public static void main(String[] args) {
        runExample1();
        runExample2();
    }
}
