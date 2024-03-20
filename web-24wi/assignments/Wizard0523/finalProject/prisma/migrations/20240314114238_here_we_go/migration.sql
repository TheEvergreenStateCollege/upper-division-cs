/*
  Warnings:

  - You are about to drop the column `belongsToId` on the `Songs` table. All the data in the column will be lost.

*/
-- DropForeignKey
ALTER TABLE "Songs" DROP CONSTRAINT "Songs_belongsToId_fkey";

-- DropIndex
DROP INDEX "Songs_name_belongsToId_key";

-- AlterTable
ALTER TABLE "Songs" DROP COLUMN "belongsToId";
